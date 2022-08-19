import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import category from "@/store/moduels/category";

const modules = {
    category,
}

const store = new Vuex.Store({
    modules,
})

export default store