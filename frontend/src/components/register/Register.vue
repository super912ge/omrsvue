<template>
    <el-form :model="registerForm" :rules="registerRule" ref="registerForm" label-width="120px" class="demo-ruleForm">
        <el-form-item label="Username" prop="username">
            <el-input size="mini" v-model="registerForm.username" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="pass">
            <el-input size="mini" type="password" v-model="registerForm.pass" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Confirm" prop="checkPass">
            <el-input size="mini" type="password" v-model="registerForm.checkPass" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="First Name" prop="firstName">
            <el-input size="mini" v-model="registerForm.firstName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Last Name" prop="lastName">
            <el-input size="mini" type="text" v-model="registerForm.lastName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="email">
            <el-input size="mini" placeholder="Please input" v-model="registerForm.email">
                <template slot="append">@proship.com</template>
            </el-input>
        </el-form-item>
        <el-form-item label="Role" prop="roles">
            <el-checkbox-group
                    v-model="registerForm.roles" :min="1">
                <el-checkbox v-for="role in rolesOptions" :label="role.id" :key="role.id">{{role.name}}</el-checkbox>
            </el-checkbox-group>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="register('registerForm')">Submit</el-button>
            <router-link to="login"><el-button >Cancel</el-button></router-link>
        </el-form-item>
    </el-form>

</template>

<script>
    import {client_id, client_secret,registerUrl,header_auth,getHeader} from '../../env.js'
    import Vue from 'vue'
    import LocalStorage from 'localStorage';
    import _ from 'lodash'
    const rolesOptions =
        [{id:1,name:'ROOT'},{id:2,name:'USER'},{id:3,name:'GIG_RESPONSIBLE'},
        {id:4,name:'RECRUITER'}, {id:5,name:'ACCOUNT_MANAGER'},{id:6, name:'ACCOUNTING_READ'},
        {id:7,name:'ACCOUNTING_WRITE'},{id:8,name:'CONTRACTING_READ'},
        {id:9, name:'CONTRACTING_WRITE'},{id:10,name:'PARTICIPANT_ADMIN'},
        {id:11,name:'PARTICIPANT_MERGE'}];


    export default {
//        components: {ElFormItem},
        data() {

            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please input the password'));
                } else {
                    if (this.registerForm.checkPass !== '') {
                        this.$refs.registerForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please input the password again'));
                } else if (value !== this.registerForm.pass) {
                    callback(new Error('Two inputs don\'t match!'));
                } else {
                    callback();
                }
            };
            return {
                registerForm: {
                    username:'',
                    email:'',
                    firstName:'',
                    lastName:'',
                    roles:[],
                    pass: '',
                    checkPass: ''
                },
                rolesOptions,
                registerRule: {

                    pass: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
                register(formName) {
                    this.$refs[formName].validate((valid) => {

                        console.log(this.registerForm);

                        const roleList = [];

                        this.registerForm.roles.map(item=> {
                          console.log(item);
                            var obj = {}
                            obj.id = item;
                            roleList.push(obj)

                        })

                        console.log('roleList',roleList)
                        const newUser = {
                            name: this.registerForm.username,
                            password: this.registerForm.pass,
                            email: this.registerForm.email+'@proship.com',
                            full_name: this.registerForm.firstName + ' ' + this.registerForm.lastName,
                            roleList
                        }

                        if (valid) {
                            let options = {emulateJSON: true};

                            this.$http.post(registerUrl, newUser).then(response => {
                                if (response.status === 200) {
                                    console.log(response.data);
                                    this.$router.push('/login')

                                } else {
                                    console.log('register fail!!');
                                    return false;
                                }
                            });
                        }
                    })
                }
        }
    }
</script>

<style scoped>

    .el-form {
        width: 40%;
        margin:50px auto;
    }
    .el-checkbox{
        margin-left: 15px;
    }
</style>
