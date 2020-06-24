// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Moment from 'moment';
import axios from 'axios'
import VueAxios from 'vue-axios';
import VueCookies from 'vue-cookies'
// import echarts from 'echarts'
import ECharts from 'vue-echarts'

// 手动引入 ECharts 各模块来减小打包体积
import 'echarts/lib/component/tooltip'
import 'echarts/lib/chart/line'
import 'echarts/lib/chart/bar'
import 'echarts/lib/chart/pie'
// 注册组件后即可使用
Vue.component('chart', ECharts)

import VCharts from 'v-charts'
Vue.use(VCharts)

Vue.use(ElementUI);
// Vue.use(VueAxios, axios)
Vue.prototype.$http = axios
Vue.config.productionTip = false
Vue.use(VueCookies)
    // Vue.prototype.$echarts = echarts
    // Vue.prototype 将echarts保存为全局变量。原则上$echarts可以为任意变量名。


Vue.filter('comverTime', function(data, format) {
    return Moment(data).format(format);
});

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    render: h => h(App)
})