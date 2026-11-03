import sys
import requests
import json

def main():
    try:
        n = float(sys.argv[1])
    except ValueError:
        sys.exit("Error1")

    try:
        res = requests.get("https://rest.coincap.io/v3/assets/bitcoin?apiKey=APIKEY")
        resjson = res.json()
        bpusd = n * float(resjson["data"]["priceUsd"])
        print(f"${bpusd:,.4f}")

    except requests.RequestException:
        sys.exit("Error2")

if __name__ == "__main__":
    main()
