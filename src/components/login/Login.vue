
<template>
    <div class="login">
        <el-form :model="loginForm" :rules="loginRule" ref="loginForm" label-width="120px" class="demo-ruleForm">
            <el-form-item label="Username" prop="username">
                <el-input v-model="loginForm.username" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Password" prop="password">
                <el-input type="password" v-model="loginForm.password" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="login('loginForm')">Submit</el-button>
                <router-link to="Register"><el-button>Register</el-button></router-link>
            </el-form-item>
        </el-form>
    </div>

</template>

<script>
    import {client_id, client_secret,loginUrl,header_auth} from '../../env.js'

    import LocalStorage from 'localStorage';
    export default {

        data() {

            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please input the password'));
                }
                callback();
            };
            var validateUsername = (rule, value, callback) => {
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
                        client_id,
                        client_secret,

                        scope:''
                    }
                    const authUser = {}
                    if (valid) {
                        let options = { emulateJSON: true };
                        this.$http.post(loginUrl, loginData,options).then(response => {
                            if(response.status===200){
                            authUser.access_token = response.data.access_token;
                            authUser.refresh_token = response.data.access_token;
                            authUser.expires_in = response.data.expires_in + now();
                            LocalStorage.setItem("authUser",JSON.stringify(authUser));
                        }
                        })
                    } else {
                        console.log('login fail!!');
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