import {addCategory, getCategoryInfo, getCategoryList, modifyCategory, removeCategory} from '@/api/category'

export const GET_CATEGORY_LIST = 'GET_CATEGORY_LIST'
export const GET_CATEGORY_INFO = 'GET_CATEGORY_INFO'
export const GET_CURRENT_CATEGORY_LIST = 'GET_CURRENT_CATEGORY_LIST'
export const ADD_CATEGORY = 'ADD_CATEGORY'
export const REMOVE_CATEGORY = 'REMOVE_CATEGORY'
export const MODIFY_CATEGORY = 'MODIFY_CATEGORY'
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
    currentCategoryData: {},
    isDialogVisible: false,
}

const getters = {
    categoryList: state => state.list,
    currentCategoryList: state => state.currentCategoryList,
    currentCategoryData: state => state.currentCategoryData,
    isDialogVisible: state => state.isDialogVisible
}

const mutations = {
    setCategoryList(state, { list }) {
        if (list != null) {
            state.list = [...list]
        } else {
            state.list = []
        }
    },
    setCurrentCategoryList(state, { list }) {
        if (list != null) {
            state.currentCategoryList = [...list]
        } else {
            state.currentCategoryList = []
        }
    },
    setCategoryDialog(state, value) {
        state.isDialogVisible = value
    },
    setCurrentCategoryData(state, data) {
        data.isDisplay = (data.isDisplay === 1)
        state.currentCategoryData = {...data}
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
    [GET_CATEGORY_INFO]({ commit }, payload) {
        return getCategoryInfo(payload).then(response => {
            commit('setCurrentCategoryData', response.data.categoryInfo)

            return response.data.categoryInfo
        })
    },
    [ADD_CATEGORY](_, payload) {
        payload.isDisplay = (payload.isDisplay === true) ? 1 : 0

        return addCategory(payload).then(response => {
            return response
        })
    },
    [REMOVE_CATEGORY]({ commit }, payload) {
        return removeCategory(payload).then(() => {
            return getCategoryList({}).then(response => {
                commit('setCategoryList', response.data)

                return response
            })
        })
    },
    [MODIFY_CATEGORY](_, payload) {
        payload.isDisplay = (payload.isDisplay === true) ? 1 : 0

        return modifyCategory(payload).then(response => {
                return response
        })
    },
    [OPEN_CATEGORY_DIALOG]({ commit }) {
        commit('setCategoryDialog', true)
    },
    [CLOSE_CATEGORY_DIALOG]({ commit }) {
        commit('setCategoryDialog', false)
        commit('setCurrentCategoryData', initCategory)
    },
}

export default {
    state,
    getters,
    actions,
    mutations,
}
