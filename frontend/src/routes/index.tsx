import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Transaction from "@/pages/Transaction.tsx";
import MainLayout from "@/components/layout/Layout.tsx";
import Stats from "@/pages/Stats.tsx";
import More from "@/pages/More.tsx";

const router = createBrowserRouter([
  {
    element: <MainLayout/>,
    children: [
      {
        path: '/',
        element: <Transaction/>
      },
      {
        path: '/stats',
        element: <Stats/>
      },
      {
        path: '/more',
        element: <More/>
      },
    ]
  }
])

export default function Router() {
  return <RouterProvider router={router}/>
}