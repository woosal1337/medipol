class CompanyStock:
    def __init__(self, name="", price=10, total_shares=1):
        self.name = name
        self.price = price
        self.total_shares = total_shares

    def update_price(self, p):
        if p < -100 or p > 100:
            return "Please, enter a percentage between -100 and 100!"

        self.price += (self.price * p) / 100

    def sell(self, m):
        required_shares = m / self.price

        if required_shares <= self.total_shares:
            self.total_shares -= required_shares
        else:
            return "Not enough shares to sell!"

    def buy(self, m):
        new_shares = m / self.price
        self.total_shares += new_shares


class LimitedStock(CompanyStock):
    def __init__(self, name, price, total_shares, ltd_shares=0):
        super().__init__(name, price, total_shares)
        self.ltd_shares = ltd_shares

        print(f"{self.name}: {self.price}, {self.total_shares}, {self.ltd_shares}")

    def sell(self, m):
        required_shares = m / self.price

        if required_shares >= self.ltd_shares:
            return f"Not allowed to sell more than {self.ltd_shares} shares!"

        if required_shares <= self.total_shares:
            self.total_shares -= required_shares
        else:
            return "Not enough shares to sell!"

        self.total_shares -= required_shares
        return f"Sold {required_shares} shares!"

    def print_info(self):
        print(f"{self.name}: {self.price}, {self.total_shares}, {self.ltd_shares}")


google_tr = LimitedStock("Google TR", 100, 1000, 200)
print(google_tr.sell(10000))
print(google_tr.update_price(4))
print(google_tr.buy(5000))
google_tr.print_info()
