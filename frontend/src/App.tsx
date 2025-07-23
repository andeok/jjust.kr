import './styles/index.css'
import Router from '@/routes'

function App() {
  return (
      <div className='w-full min-h-screen bg-gray-100 flex justify-center'>
        <div className="
        relative
        min-h-screen
        w-full
        max-w-[430px]
        bg-white
        flex flex-col
        overflow-x-hidden
        shadow
      ">
          <Router/>
        </div>
      </div>
  )
}

export default App
