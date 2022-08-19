import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import ko from "element-ui/src/locale/lang/ko";

Vue.config.productionTip = false

Vue.use(Element, {
  locale: ko
})

new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})
