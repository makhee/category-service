import Vue from 'vue'
import Router from 'vue-router'

import category from "@/router/modules/category";

Vue.use(Router)

export const routes = [
    {
        path: '/',
        redirect: 'categories',
    },
    category
]

const createRouter = () =>
    new Router({
        mode: 'history',
        routes: routes,
    })

const router = createRouter()

export default router
