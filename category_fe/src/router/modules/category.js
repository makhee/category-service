import store from '@/store'
import {GET_CATEGORY_INFO, GET_CATEGORY_LIST} from "@/store/moduels/category";
export default {
    path: '/categories',
    name: 'Category',
    component: () => import('@/views/category/index'),
    beforeEnter: (to, from, next) => {
        store.dispatch(GET_CATEGORY_LIST, {}).then(() => next())
    },
    children: [
        {
            path: 'add',
            name: 'AddCategoryDialog',
            component: () => import('@/components/categoryDialog/addCategoryDialog'),
            props: (route) => ({
                parentCategoryId: route.params.parentCategoryId,
                parentCategoryName: route.params.parentCategoryName,
            })
        },
        {
            path: ':categoryId',
            name: 'ModifyCategoryDialog',
            component: () => import('@/components/categoryDialog/modifyCategoryDialog'),
            beforeEnter: (to, from, next) => {
                store.dispatch(GET_CATEGORY_INFO, {categoryId: to.params.categoryId}).then(() => next())
            },
        }
    ]
}
