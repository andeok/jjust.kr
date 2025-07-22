interface Transaction {
    itemId: number;
    itemName: string;
    transactions: TransactionResponse[];
}

interface TransactionResponse {
    transactionId: number;
    transactionType: "PURCHASE" | "SALES";
    transactionDate: string;
    quantity: number;
    price: number;
}