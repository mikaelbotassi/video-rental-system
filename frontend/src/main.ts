import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import './assets/style.css'

import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';

import myComponents from './plugins/myComponents'

const app = createApp(App)

app.use(createPinia())
app.use(VueSweetalert2);
app.use(router)
app.use(myComponents)

app.mount('#app')
