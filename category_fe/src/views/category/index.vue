<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>카테고리</span>
          <el-button style="float: right; padding: 3px 0" type="text" @click="addCategory">카테고리 추가</el-button>
        </div>
        <el-tree :data="categoryList" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
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

import {GET_CURRENT_CATEGORY_LIST} from "@/store/moduels/category";

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
      this.currentCategoryId = data.categoryId
      this.$store.dispatch(GET_CURRENT_CATEGORY_LIST, {categoryId: data.categoryId})
    },
    addCategory() {
      this.$router.push({
        name: 'AddCategoryDialog',
        params: {},
      })
    }
  }
}
</script>
