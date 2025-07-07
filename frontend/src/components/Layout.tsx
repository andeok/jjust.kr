import Footer from './Footer.tsx'
import Header from './Header.tsx'

export default function MainLayout() {
  return (
    <>
      <Header />
      <main className='overflow-y-auto pt-[100px] pb-16 h-screen'>
        <div className='p-6 space-y-4'>
          {/* 예시 콘텐츠: 스크롤 되는지 확인 */}
          {Array.from({ length: 30 }, (_, i) => (
            <div key={i} className='p-4 bg-gray-200 rounded mb-2 text-center'>
              스크롤 콘텐츠 {i + 1}
            </div>
          ))}
        </div>
      </main>
      <Footer />
    </>
  )
}
