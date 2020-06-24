<template>
  <div id="app">
    <div id="main" :v-show="mainShow">
      <el-container>
        <el-header height="auto">
          <MyHeader @on-callback="getHeaderIndex" />
        </el-header>
        <el-main>
          <router-view />
        </el-main>
        <el-footer>Copyright © 2020 SBS-18CS-Group03 All rights reserved.</el-footer>
      </el-container>
    </div>
  </div>
</template>

<script>
import MyHeader from "@/components/header";

export default {
  name: "App",
  data() {
    return {
      headerindex: "1",
      mainShow: true
    };
  },
  methods: {
    getHeaderIndex(val) {
      console.log("get header index:", val);
      this.headerindex = val;
    }
  },
  components: {
    MyHeader
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
}

.el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
</style>
