import { createRouter, createWebHistory } from "vue-router";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "my-calculator",
      component: () => import( "../views/CalculatorView.vue")
    },
    {
      path:"/calculator-form",
      name:"calculator-form",
      component: () => import( "../views/CalculatorFormView.vue")
    },
    
    
   
  ],
});

export default router;
