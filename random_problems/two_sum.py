class Solution:
    def twoSum(self, nums: list[int], target: int) -> list[int]:
        for a in range(0, len(nums)-1):
            for b in range(a + 1, len(nums)):
                if nums[a] + nums[b] == target:
                    return [a, b]

def main():
    nums = list(map(int, input().split(',')))
    target = int(input())
    
    solution = Solution()
    x = solution.twoSum(nums, target)
    print(x)

if __name__ == "__main__":
    main()