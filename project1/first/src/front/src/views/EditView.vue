<script setup lang="ts">
import {onMounted, ref} from "vue";

import axios from "axios";
import {useRouter} from "vue-router";

const router= useRouter();

const title =ref("")
const content = ref("")
const props = defineProps({
    postId:{
        type : [Number,String],
        require : true
    },
});
const write = function (){
    axios.post("/api/posts",{
        title: title.value,
        content : content.value
    }).then(()=>{
        router.replace({name : 'home'})
    })
}
onMounted(()=>{
    axios.post()
})
</script>

<template>


    <div>
        <el-input v-model="title" placeholder="제목을 입력해주세요" />
    </div>

    <div class="my-2">
        <el-input v-model="content" type="textarea" rows="15" />
    </div>

    <div class="my-2">
        <el-button type="warning" @click="write()">수정완료</el-button>
    </div>
</template>

<style>

</style>