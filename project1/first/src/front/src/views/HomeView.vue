<script setup lang="ts">
import { useRouter} from "vue-router";
import axios from "axios";
import {ref} from "vue";

const router = useRouter();
const posts = ref([]);
axios.get("/api/posts").then((response) => {
    response.data.forEach((r : any) => {
        posts.value.push(r)
    });
});
</script>
<template>
    <main>
        <ol>
            <li v-for="post in posts" :key="post.id" >
                <router-link :to="{name: 'read', params :{postId : post.id}}">
                    {{ post.title }}
                </router-link>
                <div>{{ post.content }}</div>
            </li>
        </ol>
    </main>
</template>

<style scoped>
li {
    magoin-bottom: 1rem;
}

li:last-child {
    margin-bottom: 0;
}
</style>

