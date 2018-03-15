import Full from './components/Full.vue'
import Login from './components/login/Login.vue'
import Register from './components/register/Register.vue'
import TodoList from './components/tool/todoList/TodoList.vue'
import SearchCandidate from './components/search/candidate/Candidate.vue'
import SearchContract from './components/search/contract/Contract.vue'
import SearchBookingProposal from './components/search/booking proposal/BookingProposal.vue'
import SearchOpening from './components/search/opening/Opening.vue'
import SearchGig from './components/search/gig/Gig.vue'
import SearchBand from './components/search/band/Band.vue'
import SearchClient from './components/search/client/Client.vue'
import SearchUser from './components/search/user/User.vue'
import NewCandidate from './components/new/candidate/Candidate.vue'
import DisplayCandidate from './components/display/candidate/Candidate.vue'
export const routes = [
    { path: '/',
      component: Full,
        name:'User Info',
        meta: { requiresAuth: true }
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
                component:SearchBand,
                meta: {

                }
            },
            {
                name: 'Gig',
                path: 'gig',
                component:SearchGig,
                meta: {

                }
            },
            {
                name: 'Opening',
                path: 'opening',
                component:SearchOpening,
                meta: {

                }
            },
            {
                name: 'Contract',
                path: 'contract',
                component:SearchContract,
                meta: {

                }
            },
            {
                name: 'Booking Proposal',
                path: 'booking proposal',
                component:SearchBookingProposal,
                meta: {

                }
            },
            {
                name: 'Client',
                path: 'client',
                component: SearchClient,
                meta: {

                }
            },
            {
                name: 'User',
                path: 'user',
                component: SearchUser,
                meta: {

                }
            }
        ]

    },
    {
        name: 'New',
        path: '/new',
        component:Full,
        meta: {

        },
        children: [
            {
                name: 'NewCandidate',
                path: 'candidate',
                component:NewCandidate,
                meta: {

                }
            },
            {
                name: 'NewBand',
                path: 'band',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'NewGig',
                path:'gig',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'NewClient',
                path: 'client',
                component:SearchCandidate,
                meta: {

                }
            },
            {
                name: 'NewUser',
                path: 'user',
                component:SearchCandidate,
                meta: {

                }
            }
        ]
    },
  {
    name: 'Display',
    path: '/display',
    component:Full,
    meta: {hidden:true},
    children: [
      {
        name: 'DisplayCandidate',
        path: 'candidate/:id',
        component:DisplayCandidate,
        meta: {

        }
      },
      {
        name: 'DisplayBand',
        path: 'band',
        component:SearchCandidate,
        meta: {

        }
      },
      {
        name: 'DisplayGig',
        path:'gig',
        component:SearchCandidate,
        meta: {

        }
      },
      {
        name: 'DisplayClient',
        path: 'client',
        component:SearchCandidate,
        meta: {

        }
      },
      {
        name: 'DisplayUser',
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
                name: 'Invoice',
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
                component: SearchCandidate, SearchContract,
                meta:{

                }
            }
        ],

        meta: {}
    },
    { path: '/user/login', component: Login, meta: {hidden:true}},
    { path: '/register', component:Register, meta: {hidden:true}}

]

