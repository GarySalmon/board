import { createRouter, createWebHashHistory } from "vue-router"

const routes = [
  {
    path: "/",
    name: "login",
    component: () => import(/* webpackChunkName: "login" */ "../views/LoginView.vue"),
  },
  {
    path: "/blog",
    name: "blog",
    component: () => import(/* webpackChunkName: "blog" */ "../views/BlogView.vue"),
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

router.beforeEach((to, from, next) => {
  // const publicPages = ["/login"]
  // const authRequired = !publicPages.includes(to.path)
  // const loggedIn = localStorage.getItem("user")
  // if (authRequired && !loggedIn) {
  //   return next("/blog")
  // }
  next()
})

export default router
