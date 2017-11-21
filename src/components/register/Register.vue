<template>
    <el-form :model="registerForm" :rules="registerRule" ref="registerForm" label-width="120px" class="demo-ruleForm">
        <el-form-item label="Username" prop="username">
            <el-input v-model="registerForm.username" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="pass">
            <el-input type="password" v-model="registerForm.pass" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Confirm" prop="checkPass">
            <el-input type="password" v-model="registerForm.checkPass" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="First Name" prop="firstName">
            <el-input v-model="registerForm.firstName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Last Name" prop="lastName">
            <el-input type="text" v-model="registerForm.lastName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="email">
            <el-input placeholder="Please input" v-model="registerForm.email">
                <template slot="append">@proship.com</template>
            </el-input>
        </el-form-item>
        <el-form-item label="Role" prop="roles">
            <el-checkbox-group
                    v-model="registerForm.roles" :min="1">
                <el-checkbox v-for="role in rolesOptions" :label="role" :key="role">{{role}}</el-checkbox>
            </el-checkbox-group>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm('registerForm')">Submit</el-button>
            <router-link to="login"><el-button >Cancel</el-button></router-link>
        </el-form-item>
    </el-form>

</template>

<script>
    import ElFormItem from "../../../node_modules/element-ui/packages/form/src/form-item.vue";
    const rolesOptions = ['ACCOUNTING_READ', 'ACCOUNTING_WRITE', 'ACCOUNT_MANAGER', 'CONTRACTING_READ','CONTRACTING_WRITE','GIG_RESPONSIBLE',
    'PARTICIPANT_ADMIN','PARTICIPANT_MERGE','RECRUITER','ROOT','USER'];
    export default {
        components: {ElFormItem},
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
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(this.registerForm)
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
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