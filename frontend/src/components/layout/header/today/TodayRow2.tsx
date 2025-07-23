import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow,} from '@/components/ui/table'

interface ItemListHeaderProps {
  purchasePrice: number
  salesPrice: number
  sumPrice: number
  purchaseCount: number
  saleCount: number
}

export default function TodayRow2({
                                    purchasePrice,
                                    salesPrice,
                                    sumPrice,
                                    purchaseCount,
                                    saleCount
                                  }: ItemListHeaderProps) {
  return (
      <div className='flex items-center justify-between h-20 px-4'>
        <Table className='table-fixed w-full'>
          <TableHeader>
            <TableRow>
              <TableHead
                  className='w-1/3 text-center'>구매({purchaseCount ? purchaseCount : 0})</TableHead>
              <TableHead
                  className='w-1/3 text-center'>판매({saleCount ? saleCount : 0})</TableHead>
              <TableHead className='w-1/3 text-center'>합계</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow>
              <TableCell
                  className='w-1/3 text-center'>{purchasePrice.toLocaleString()}</TableCell>
              <TableCell className='w-1/3 text-center'>{salesPrice.toLocaleString()}</TableCell>
              <TableCell className='w-1/3 text-center'>{sumPrice.toLocaleString()}</TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </div>
  )
}
