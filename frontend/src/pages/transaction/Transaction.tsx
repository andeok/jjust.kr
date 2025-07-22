import {Card, CardContent,} from "@/components/ui/card"
import {Badge} from "@/components/ui/badge.tsx";

export default function Transaction() {
  return (
      <>
        <Card className="flex flex-col m-1"> {/* Card에 높이와 flex 추가 */}
          <CardContent
              className="flex items-stretch h-full p-0"> {/* CardContent에 h-full, p-0로 패딩 제거 */}
            {/* 왼쪽 영역 */}
            <div className="flex-1 flex flex-col m-1">
              <div className="text-sm text-gray-500">
                <div className="flex items-center grid-cols-3">
                            <span className="flex-auto">
                                <Badge className="bg-blue-600">구매</Badge>
                            </span>
                  <span className="flex-auto">2025. 07. 15</span>
                  <span className="flex-auto">₩ 50,000</span>
                </div>
              </div>
              <div className="mt-1">[카메라] eos r8</div>
            </div>
            {/* 중앙 구분선 */}
            <div className="w-px bg-border self-stretch"/>
            {/* 오른쪽 영역 */}
            <div className="flex-1 flex flex-col m-1">
              {Math.random() > 0.5 ?
                  <div className="text-sm text-gray-500">
                    <div className="flex items-center grid-cols-3">
                            <span className="flex-auto">
                                <Badge className="bg-green-600">보유</Badge>
                            </span>
                      <span className="flex-auto text-right">50 일 째</span>
                    </div>
                  </div>
                  :
                  <div className="text-sm text-gray-500">
                    <div className="flex items-center grid-cols-3">
                            <span className="flex-auto">
                                <Badge className="bg-red-600">판매</Badge>
                            </span>
                      <span className="flex-auto">2025. 07. 15</span>
                      <span className="flex-auto">₩ 50,000</span>
                    </div>
                    <div className="text-right text-yellow-600">₩ -5,000</div>

                  </div>
              }

            </div>
          </CardContent>
        </Card>
      </>
  )
}