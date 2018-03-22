<template>
<div>
    <p>userinfo</p>
    <!--<p>{{authUser.access_token}}</p>-->

  <p>Full Name: {{user.full_name}}</p>
  <p>Email: {{user.email}}</p>
</div>

</template>
<script>
    import ls from 'localStorage'

    export default {

      props:{

        },
        data(){
            let user = JSON.parse(ls.getItem("userInfo"));
            if(!user){
              user = {
                full_name: 'No user found',
                email: 'No user found'
              }
            }
            console.log(ls.getItem("userInfo"));
            console.log('parse authUser',user);
            return {user};
        }
    }
</script>
