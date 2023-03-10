import { createRouter, createWebHistory } from "vue-router";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/my-calculator",
      name: "my-calculator",
      component: () => import( "../views/CalculatorView.vue")
    },
    {
      path:"/calculator-form",
      name:"calculator-form",
      component: () => import( "../views/CalculatorFormView.vue")
    },
    {
      path:"/",
      name:"log-in",
      component: () => import( "../views/LoginView.vue")
    },
    {
      path:"/sign-up",
      name:"sign-up",
      component: () => import( "../views/SignUpView.vue")
    },
    
   
  ],
});


export default router;
