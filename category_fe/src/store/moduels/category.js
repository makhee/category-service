import {addCategory, getCategoryList} from '@/api/category'

export const GET_CATEGORY_LIST = 'GET_CATEGORY_LIST'
export const GET_CURRENT_CATEGORY_LIST = 'GET_CURRENT_CATEGORY_LIST'
export const ADD_CATEGORY = 'ADD_CATEGORY'
export const OPEN_CATEGORY_DIALOG = 'OPEN_CATEGORY_DIALOG'
export const CLOSE_CATEGORY_DIALOG = 'CLOSE_CATEGORY_DIALOG'

const initCategory = {
    categoryId: 0,
    categoryName: null,
    parentCategoryId: 0,
    isDisplay: 0,
    categorySeq: 0
}

const state = {
    list: [],
    currentCategoryList: [],
    currentData: {
    },
    isDialogVisible: false,
}

const getters = {
    categoryList: state => state.list,
    currentCategoryList: state => state.currentCategoryList,
    isDialogVisible: state => state.isDialogVisible
}

const mutations = {
    setCategoryList(state, { list }) {
        if (list) {
            state.list = [...list]
        }
    },
    setCurrentCategoryList(state, { list }) {
        if (list) {
            state.currentCategoryList = [...list]
        }
    },
    setCategoryDialog(state, value) {
        state.isDialogVisible = value
    },
    setCurrentData(state, category) {
        state.currentData = category
    }
}

const actions = {
    [GET_CATEGORY_LIST]({ commit }, payload) {
        return getCategoryList(payload).then(response => {
            commit('setCategoryList', response.data)

            return response.data
        })
    },
    [GET_CURRENT_CATEGORY_LIST]({ commit }, payload) {
        return getCategoryList(payload).then(response => {
            commit('setCurrentCategoryList', response.data)

            return response.data
        })
    },
    [ADD_CATEGORY]({ commit }, payload) {
        payload.isDisplay = (payload.isDisplay === true) ? 1 : 0

        return addCategory(payload).then(response => {
            commit('setCategoryList', response.data)

            return response.data
        })
    },
    [OPEN_CATEGORY_DIALOG]({ commit }) {
        commit('setCategoryDialog', true)
    },
    [CLOSE_CATEGORY_DIALOG]({ commit }) {
        commit('setCategoryDialog', false)
        commit('setCurrentData', initCategory)
    },
}

export default {
    state,
    getters,
    actions,
    mutations,
}
