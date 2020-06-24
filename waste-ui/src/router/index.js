import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Home from '@/pages/home'
import errorpage from '@/pages/notfound'

const Register = () =>
    import ('@/pages/register')
const NoticeBoard = () =>
    import ('@/pages/noticeboard')
const Knowledge = () =>
    import ('@/pages/knowledge')
const Order = () =>
    import ('@/pages/order')
const DataAnalysis = () =>
    import ('@/pages/data')
const UserPoint = () =>
    import ('@/components/userpoint')

const UserIndex = () =>
    import ('@/pages/usercenter')
const AdminList = () =>
    import ('@/pages/backend/adminlist')


Vue.use(Router)

export default new Router({
    mode: 'history',
    base: "/waste",
    routes: [{
            path: '/',
            name: 'Home',
            component: Home
        },
        {
            path: '/signUp',
            name: 'SignUp',
            component: Register
        },
        {
            path: '/noticeboard',
            name: 'NoticeBoard',
            component: NoticeBoard
        },
        {
            path: '/knowledge',
            name: 'Knowledge',
            component: Knowledge
        },
        {
            path: '/order',
            name: 'OrderIndex',
            component: Order
        },
        {
            path: '/data',
            name: 'DataAnalysis',
            component: DataAnalysis
        },
        {
            path: '/user',
            name: 'UserCenter',
            component: UserIndex
        },
        {
            path: '/user/point',
            name: 'UserPoint',
            component: UserPoint
        },
        {
            path: '/admin/all',
            name: 'AdminList',
            component: AdminList
        },
        {
            path: '/helloworld',
            name: 'HelloWorld',
            component: HelloWorld
        },
        {
            path: '*',
            name: 'not found',
            component: errorpage
        }
    ]
})

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}