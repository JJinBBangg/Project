<script setup lang="ts">

import axios from "axios";
import {onMounted, ref} from "vue";
import {useRouter} from "vue-router";

const router = useRouter();
const props = defineProps({
    postId:{
        type : [Number,String],
        require : true
    },
});
const moveToEdit = ()=>{
    router.push({name : "edit", params:{postId : props.postId}})
}
const post = ref({
    id: 0,
    title: "",
    content:""
});
onMounted(()=>{
    axios.get(`/api/posts/${props.postId}`).then((response)=>{
        post.value = response.data;
    })
})
</script>

<template>
  <h2>제목 : {{post.title}}</h2>
  <p>내용 : {{post.content}}</p>
  <el-button type="warning" @click="moveToEdit()">수정</el-button>
</template>