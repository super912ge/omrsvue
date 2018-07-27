
<template>
    <div class="login">
        <el-form :model="loginForm" :rules="loginRule" ref="loginForm" label-width="120px" class="demo-ruleForm">
            <el-form-item label="Username" prop="username">
                <el-input size="mini" v-model="loginForm.username" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Password" prop="password">
                <el-input size="mini" type="password" v-model="loginForm.password" auto-complete="off"></el-input>

            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="login('loginForm')">Submit</el-button>
                <router-link to="/register"><el-button>Register</el-button></router-link>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import {client_id, client_secret,loginUrl,header_auth,getHeader} from '../../env.js'
    import Vue from 'vue'
    import LocalStorage from 'localStorage';
    import _ from 'lodash'

    import axios from 'axios'

    export default {

        data() {

            let validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please input the password'));
                }
                callback();
            };
            let validateUsername = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please input the username'));
                }
                callback();
            };

            return {
                loginForm: {
                    username: '',
                    password: ''
                },
                loginRule: {
                    username: [
                        { validator: validateUsername, trigger: 'blur' }
                    ],
                    password: [
                        { validator: validatePass, trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            login(formName) {

                this.$refs[formName].validate((valid) => {

                    const loginData = {
                        username: this.loginForm.username,
                        password: this.loginForm.password,
                        grant_type: 'password',
                        scope:''
                    };
                    const authUser = {};

                    const userInfo = {};

                    if (valid) {
                        let options = { emulateJSON: true};

                      this.$http.post(loginUrl, loginData,options,
                            {headers: { Authorization: header_auth }}
                            ).then(response => {
                            if(response.status===200){
                              LocalStorage.setItem("userId",response.data.userId);
                              LocalStorage.setItem("username",JSON.stringify(response.data.username));
                            authUser.access_token = response.data.access_token;
                            authUser.refresh_token = response.data.access_token;
                            authUser.expires_in = response.data.expires_in *1000 + _.now();
                              LocalStorage.setItem("authUser",JSON.stringify(authUser));
                            this.$http.get('user/'+response.data.userId,{headers: getHeader()})
                                .then(response => {
                                    if(response.status === 200){
                                      userInfo.full_name = response.data.full_name;
                                      userInfo.email = response.data.email;
                                      LocalStorage.setItem("userInfo",JSON.stringify(userInfo));
                                        this.$router.push('/')
                                    }

                                })
                            }
                        })
                    } else {
                        return false;
                    }
                });
            }
        }
    }
</script>
<style scoped>

    .el-form {
        width: 30%;
        margin:200px auto;
    }
</style>
