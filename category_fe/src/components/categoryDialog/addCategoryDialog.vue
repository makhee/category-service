<template>
  <el-dialog
    title="카테고리 추가"
    :visible.sync="isDialogVisible"
    top="15vh"
    width="30%"
    @close="close"
  >
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
      <el-form-item label="카테고리명" prop="categoryName">
        <el-input v-model="ruleForm.categoryName"></el-input>
      </el-form-item>
      <el-form-item label="상위 카테고리" prop="parentCategoryName">
        <el-input v-model="ruleForm.parentCategoryName" readonly></el-input>
      </el-form-item>
      <el-form-item label="노출 순서" prop="categorySeq">
        <el-input v-model="ruleForm.categorySeq"></el-input>
      </el-form-item>
      <el-form-item label="노출여부" prop="isDisplay">
        <el-switch v-model="ruleForm.isDisplay"></el-switch>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="clickConfirm">적용</el-button>
        <el-button @click="clickCancel">취소</el-button>
    </span>
  </el-dialog>
</template>

<script>
import {ADD_CATEGORY, CLOSE_CATEGORY_DIALOG, GET_CATEGORY_LIST, OPEN_CATEGORY_DIALOG} from '@/store/moduels/category';
import {Message} from "element-ui";

export default {
  props: {
    parentCategoryId: {
      type: Number,
      default: 0
    },
    parentCategoryName: {
      type: String,
      default: 'ROOT'
    }
  },
  data() {
    return {
      ruleForm: {
        categoryName: '',
        parentCategoryName: this.parentCategoryName,
        parentCategoryId: this.parentCategoryId,
        isDisplay: true,
        categorySeq: 0,
      },
      rules: {
        categoryName: [
          {required: true, message: '카테고리명은 필수입니다.', trigger: 'blur'},
          {min: 3, max: 15, message: '카테고리명은 3자리 ~ 15자리 이내로 입력해야합니다.', trigger: 'blur'}
        ],
        parentCategoryId: [
          {required: true, message: '상위 카테고리 정보는 필수입니다.', trigger: 'blur'}
        ],
        categorySeq: [
          {required: true, message: '노출순서는 필수입니다.', trigger: 'blur'}
        ],
      }
    }
  },
  computed: {
    isDialogVisible() {
      return (this.$store.getters['isDialogVisible'] === true)
    },
  },
  created() {
    this.$store.dispatch(OPEN_CATEGORY_DIALOG)
  },
  destroyed() {
    this.closeHandler()
  },
  methods: {
    closeHandler() {
      this.$store.dispatch(CLOSE_CATEGORY_DIALOG)
      this.$router.push({name: 'Category'})
    },
    /**
     * close 이벤트 실행 케이스
     * Case 1. visible 속성이 false 로 업데이트 될 때
     * Case 2. 우측 상단 엑스 버튼이나 Outside 영역을 클릭했을 경우
     */
    close() {
      this.closeHandler()
    },
    // closed() {
    //   this.$store.dispatch(CLOSE_CATEGORY_DIALOG)
    // },
    clickCancel() {
      this.closeHandler()
    },
    clickConfirm() {
      this.$refs['ruleForm'].validate(async (valid) => {
        if (valid) {
          this.$store.dispatch(ADD_CATEGORY, {...this.ruleForm}).then((res) => {
            this.$store.dispatch(GET_CATEGORY_LIST, {})
            Message({
              message: res.message,
              type: 'success',
              duration: 5 * 1000,
            })
          })
          this.closeHandler()
        } else {
          return false;
        }
      });
    },
  }
}
</script>

<style scoped>

</style>
