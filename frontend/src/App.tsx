import './styles/index.css'
import Router from "@/routes";

function App() {
  return (
    <div className='w-full min-h-screen bg-gray-100 flex justify-center'>
      <div className='relative w-full max-w-[430px] min-h-screen bg-white'>
        <Router />
      </div>
    </div>
  )
}

export default App
