import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Items from "@/pages/item/Items.tsx";
import MainLayout from "@/components/layout/Layout.tsx";
import Stats from "@/pages/Stats.tsx";
import More from "@/pages/More.tsx";
import Login from "@/pages/login/Login.tsx";

const router = createBrowserRouter([
  {
    element: <MainLayout/>,
    children: [
      {
        path: '/',
        element: <Items/>
      },
      {
        path: '/stats',
        element: <Stats/>
      },
      {
        path: '/more',
        element: <More/>
      },
      {
        path: '/login',
        element: <Login/>
      }
    ]
  }
])

export default function Router() {
  return <RouterProvider router={router}/>
}