<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>카테고리</span>
          <el-button style="float: right; padding: 3px 0" type="text" @click="addCategory">기본 카테고리 추가</el-button>
        </div>
        <el-tree :data="categoryList" :props="defaultProps" @node-click="handleNodeClick">
          <span class="custom-tree-node" slot-scope="{ node, data }">
            <span>{{ node.label }}</span>
            <span>
              <el-button
                  type="text"
                  size="mini"
                  @click="() => addCategory(data)">
                추가
              </el-button>
              <el-button
                  type="text"
                  size="mini"
                  @click="() => modifyCategory(data)">
                수정
              </el-button>
              <el-button
                  type="text"
                  size="mini"
                  @click="() => removeCategory(node, data)">
                삭제
              </el-button>
            </span>
          </span>
        </el-tree>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>선택한 카테고리 그룹 조회</span>
        </div>
        <el-tree :data="currentCategoryList" :props="defaultProps"></el-tree>
      </el-card>
    </el-col>
    <router-view/>
  </el-row>
</template>

<script>

import {GET_CURRENT_CATEGORY_LIST, REMOVE_CATEGORY} from '@/store/moduels/category'
import {Message, MessageBox} from 'element-ui'

export default {
  name: 'CategoryIndex',
  computed: {
    categoryList() {
      return this.$store.getters['categoryList'] || []
    },
    currentCategoryList() {
      return this.$store.getters['currentCategoryList'] || []
    }
  },
  data() {
    return {
      defaultProps: {
        children: 'childCategoryList',
        label: 'categoryName'
      },
      currentCategoryId: 0,
    }
  },
  methods: {
    handleNodeClick(data) {
      this.$store.dispatch(GET_CURRENT_CATEGORY_LIST, {categoryId: data.categoryId})
    },
    addCategory(data) {
      this.$router.push({
        name: 'AddCategoryDialog',
        params: {
          parentCategoryId: data.categoryId,
          parentCategoryName: data.categoryName
        },
      })
    },
    removeCategory(node, data) {
      MessageBox.confirm('카테고리를 삭제하시겠습니까?<br>하위 카테고리도 모두 삭제됩니다.', '카테고리 삭제', {
        type: 'warning',
        dangerouslyUseHTMLString: true
      }).then(() => {
        this.$store.dispatch(REMOVE_CATEGORY, {categoryId: data.categoryId}).then((res) => {
          Message({
            message: res.message,
            type: 'success',
            duration: 5 * 1000,
          })
        })
      }).catch(() => {
        this.$message({
          message: '취소 하였습니다.',
          type: 'info',
          duration: 5 * 1000,
        });
      });
    },
    modifyCategory(data) {
      this.$router.push({
        name: 'ModifyCategoryDialog',
        params: {
          categoryId: data.categoryId,
        },
      })
    },
  },
}
</script>

<style>
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>