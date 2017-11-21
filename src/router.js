import Full from './components/Full.vue'
import Login from './components/login/Login.vue'
import Register from './components/register/Register.vue'
import TodoList from './components/tool/todoList/TodoList.vue'
import SearchCandidate from './components/search/candidate/Candidate.vue'

export const routes = [
    { path: '/', component: Full,
        name:'User Info',
        //meta: { requiresAuth: true },
    meta: {

}
   },
    { name: 'Search',
        path: '/search',
        component:Full,
        meta: {

        },
        children:[
            {
                name: 'Candidate',
                path: 'candidate',
                component:SearchCandidate,
                meta: {

                }

            },
            {
                name: 'Band',
                path: 'band',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'Gig',
                path:'gig',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'Opening',
                path: 'opening',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'Contract',
                path: 'contract',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'Booking Proposal',
                path: 'booking proposal',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'Client',
                path: 'client',
                component: SearchCandidate,
                meta: {

                }
            },
            {
                name: 'User',
                path: 'user',
                component: SearchCandidate,
                meta: {

                }
            }
        ]

    },
    {
        name: 'New',
        path: 'new',
        component:Full,
        meta: {

        },
        children: [
            {
                name: 'Candidate',
                path: 'candidate',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'Band',
                path: 'band',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'Gig',
                path:'gig',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'Client',
                path: 'client',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'User',
                path: 'user',
                component:SearchCandidate,
                meta: {

                }
            }
        ]
    },
    {
        name: 'Tool',
        path: '/tool',
        component:Full,
        meta: {

        },
        children: [
            {
                name: 'Requisition',
                path: 'requisition',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'Dry dock',
                path: 'drydock',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'To Do List',
                path: 'todolist',
                component:TodoList,
                meta: {

                }
            }
        ]
    },
    {
        name: 'Revenue',
        path: '/revenue',
        component:Full,
        meta: {

        },
        children: [
            {
                name: 'invoice',
                path: 'invoice',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'Invoice Summary',
                path: 'invoicesummary',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'Revenue Report',
                path: 'revenuereport',
                component:SearchCandidate,
                meta: {

                }
            }
        ]
    },
    {
        name: 'Stats',
        path: '/stats',
        component:Full,

    meta: {

},
        children:[
            {
                name: 'Daily Booking Stats',
                path: 'daily',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'Weekly Booking Stats',
                path: 'weekly',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'Monthly Booking Stats',
                path: 'monthly',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'Flexible Booking Stats',
                path: 'flexible',
                component:SearchCandidate,
                meta: {

                }
            }


        ]

    },
    {
        name: 'Incentive',
        path: '/incentive',
        component:Full,
        children:[
            {
                name:'Incentive',
                path:'',
                component: SearchCandidate,
                meta:{

                }
            }
        ],

        meta: {

        }
    },
    { path: '/login', component: Login,
    meta: {
        hidden:true
}},
    { path: '/register', component:Register,
    meta: {
        hidden:true
}}

]

