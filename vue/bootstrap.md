### git 설치

```
$> apt-get -y install git
```

### node 설치

```
$> apt install -y npm
$> npm install -g nodemon
```

### 프로젝트 폴더 생성

```
$> cd /
$> su mkdir workspace
$> cd /workspace
```

### vue, vue/cli 설치

```
$> npm install -g vue
$> npm install -g @vue/cli
```

### 프로젝트 설치

-   vue cli 이용

```
$> vue create argon
$> cd argon
$> npm run serve
```

### vue router 설치

```
$> npm install vue-router --save
```

### boostrap 설치

```
$> npm install vue bootstrap bootstrap-vue
```

### /root/src/main.js 수정

-   bootstrap 설정
-   router 설정

```vue.js
import Vue from "vue";
import App from "./App.vue";
import router from "./router";

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

// Import Bootstrap an BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

Vue.config.productionTip = false;

new Vue({
    router,
    render: (h) => h(App),
}).$mount("#app");
```

### /root/src/App.vue 수정

```vue.js
<template>
    <div id="app">
        <div id="content" class="content">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
export default {
    name: "App",
    mounted() {
        // 환경 변수 출력
        console.log(process.env)
    },
    components: {
    },
};
</script>
```

### /root/src/router.js 생성

```vue.js
import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from './views/Home';

Vue.use(VueRouter);

const router = new VueRouter({
    name : "history",
    routes: [
        {
            path: "/",
            component: Home,
        }
    ]
});

export default router;
```

### /root/src/views/Home.vue 생성

```vue.js
<template>
    <div>
        <b-table striped hover :items="items"></b-table>
    </div>
</template>

<script>
export default {
    data() {
        return {
            items: [
                { age: 40, first_name: "Dickerson", last_name: "Macdonald" },
                { age: 21, first_name: "Larsen", last_name: "Shaw" },
                { age: 89, first_name: "Geneva", last_name: "Wilson" },
                { age: 38, first_name: "Jami", last_name: "Carney" },
            ],
        };
    },
};
</script>
```

### /root/.env.development 파일 생성

```vue.js
VUE_APP_ENVTEST=devlopment
VUE_APP_PORT=4000
BASE_URL="/"
```

### /root/.env.production 파일 생성

```vue.js
VUE_APP_ENVTEST=production
VUE_APP_PORT=4000
BASE_URL="/"
```

### /root/vue.config.js 파일생성

```vue.js
const path = require("path");

module.exports = {
    // build 결과물 출력 폴더 지정
    outputDir: path.resolve(__dirname, "./output"),

    devServer: {
        // 환경변수 포트 또는 기본 포트로 개발서버 실행
        port: process.env.VUE_APP_PORT || 8080
    }
};
```
