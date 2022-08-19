import store from '@/store'
import {GET_CATEGORY_LIST} from "@/store/moduels/category";
export default {
    path: '/categories',
    name: 'Category',
    component: () => import('@/views/category/index'),
    beforeEnter: async (to, from, next) => {
        await store.dispatch(GET_CATEGORY_LIST, {}).then(() => next())
    },
    children: [
        {
            path: 'add',
            name: 'AddCategoryDialog',
            component: () => import('@/components/AddCategoryDialog/AddCategoryDialog'),
            beforeEnter: async (to, from, next) => {
                await store.dispatch(GET_CATEGORY_LIST, {}).then(() => next())
            },
        }
    ]
}
