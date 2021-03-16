# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
import copy


def set2CycleInPermutation(permutation, firstElement, secondElement):
    permutation[firstElement] = secondElement
    permutation[secondElement] = firstElement


def find2Cycles(cycleNumber, permutationSize, alreadyUsedElements, permutation, firstElement, result):
    firstElementCopy = firstElement
    for secondElement in range(cycleNumber, permutationSize):
        alreadyUsedElementsCopy = alreadyUsedElements.copy()
        firstElement = firstElementCopy
        if secondElement not in alreadyUsedElementsCopy:
            alreadyUsedElementsCopy.append(secondElement)
            set2CycleInPermutation(permutation, firstElement, secondElement)
            firstElement += 1
            while firstElement in alreadyUsedElementsCopy:
                firstElement += 1
            alreadyUsedElementsCopy.append(firstElement)
            if cycleNumber == permutationSize / 2:
                result.append(permutation.copy())
                return
            find2Cycles(cycleNumber + 1, permutationSize, alreadyUsedElementsCopy, permutation, firstElement, result)


def getAll2CyclesPermutations(permutationSize=8):  # 105 results
    if permutationSize % 2 == 1:
        raise Exception("nieparzysta dlugosc permutacji: nie mozna podzielic na dwuelementowe cykle")
    result = []
    permutation = list(range(permutationSize))  # numeracja od 0!
    cycleNumber = 1
    firstElement = 0
    alreadyUsedElements = [firstElement]
    find2Cycles(cycleNumber, permutationSize, alreadyUsedElements, permutation, firstElement, result)
    return result


def getAll7CyclesPermutations(result=[], cycle=[0]):  # 720 results
    for element in range(1, 7):
        cycleCopy = cycle.copy()
        if element not in cycleCopy:
            cycleCopy.append(element)
            getAll7CyclesPermutations(result, cycleCopy)
            if len(cycleCopy) == 7:
                result.append(cycleToPermutation(cycleCopy.copy()))
    return result


def multiplyPermutations(permutation1, permutation2):
    if len(permutation1) != len(permutation2):
        raise Exception("złożenie permutacji o różnych długościach")
    result = list(range(len(permutation1)))
    for i in range(0, len(permutation1)):
        result[i] = permutation1[permutation2[i]]
    return result


def is2CyclesPermutation(permutation):
    for i in range(0, len(permutation)):
        if permutation[permutation[i]] != i or permutation[i] == i:
            return False
    return True


def cycleToPermutation(cycle):
    permutation = [0, 1, 2, 3, 4, 5, 6, 7]
    for i in range(0, len(cycle)):
        permutation[cycle[i]] = cycle[(i + 1) % len(cycle)]
    return permutation


def permutationToCycles(permutation):
    used = []
    cycle = []
    while len(used) < 8:
        cycleN = []
        for element in range(0, len(permutation)):
            if element not in used:
                cycleN.append(element)
                used.append(element)
                while permutation[element] != cycleN[0]:
                    element = permutation[element]
                    cycleN.append(element)
                    used.append(element)
                cycle.append(cycleN.copy())
                break
    return cycle


def hasOneNotChaoticElement(permutation):
    notChaoticElements = 0
    for i in range(0, len(permutation)):
        if permutation[i] == i:
            notChaoticElements += 1
            if notChaoticElements > 1:
                return False
    if notChaoticElements == 1:
        return True
    return False


def add1ToCycleGroup(cyclesGroup):
    return list(map(lambda cycle: list(map(lambda element: element + 1, cycle)), cyclesGroup))


def add1ToPermutation(permutation):
    return list(map(lambda element: element + 1, permutation))


def findGroupOfThree2CyclesPermutations(twoCyclesPermutations, limit=210):  # 210 results
    wasUsed = []
    amount = 0
    for per1 in twoCyclesPermutations:
        for per2 in twoCyclesPermutations:
            if per1 != per2:
                per3 = multiplyPermutations(per1, per2)
                if is2CyclesPermutation(per3) and multiplyPermutations(per3, per1) == per2:
                    if not [per1, per2, per3] in wasUsed:
                        yield per1, per2, per3
                        amount += 1
                        wasUsed.append([per1, per2, per3])
                        wasUsed.append([per2, per3, per1])
                        wasUsed.append([per3, per2, per1])
                        wasUsed.append([per1, per3, per2])
                        wasUsed.append([per2, per1, per3])
                        wasUsed.append([per3, per1, per2])
                        if limit == amount:
                            return


def findGroupOfSeven2CyclesPermutations(limit=None):  # 210 results (jeden dla kazdej grupy 3 permutacji)
    result = []
    twoCyclesPermutations = getAll2CyclesPermutations()
    amount = 0
    for i in findGroupOfThree2CyclesPermutations(twoCyclesPermutations):
        per1, per2, per3 = i
        wasUsed = []
        for per4 in twoCyclesPermutations:
            if per4 != per1 and per4 != per2 and per4 != per3 and per4 not in wasUsed:
                per5 = multiplyPermutations(per1, per4)
                if not is2CyclesPermutation(per5) or per5 == per2 or per5 == per3:
                    continue
                per6 = multiplyPermutations(per2, per4)
                per7 = multiplyPermutations(per3, per4)
                if not is2CyclesPermutation(per6) or not is2CyclesPermutation(per7):
                    continue
                if multiplyPermutations(per5, per2) in [per3, per6, per7]:
                    wasUsed.append(per4)
                    wasUsed.append(per5)
                    wasUsed.append(per6)
                    wasUsed.append(per7)
                    result.append([per1, per2, per3, per4, per5, per6, per7])
                    amount += 1
                    if limit and limit == amount:
                        return result
    return result


def mainFunction(limit=None, printResult=False):
    allSeven = getAll7CyclesPermutations()  # 720 results
    allGroups = findGroupOfSeven2CyclesPermutations()  # 210 results
    amount = 0
    for group in allGroups:
        allSevenCopy = allSeven.copy()
        while len(allSevenCopy) > 0:
            nextTry = False
            sevenCyclePermutation = allSevenCopy[0].copy()
            twoCyclePermutationNumber = 0
            for twoCyclePermutation in group:  # dla kazdego z 7 wynikow podwojnych cykli
                twoCyclePermutationNumber += 1
                sevenCyclePermutationTmp = sevenCyclePermutation.copy()
                while sevenCyclePermutationTmp != [0, 1, 2, 3, 4, 5, 6, 7]:
                    if twoCyclePermutationNumber == 1:
                        allSevenCopy.remove(sevenCyclePermutationTmp)
                    if not hasOneNotChaoticElement(multiplyPermutations(sevenCyclePermutationTmp, twoCyclePermutation)) \
                            or not hasOneNotChaoticElement(multiplyPermutations(twoCyclePermutation, sevenCyclePermutationTmp)):
                        nextTry = True
                    sevenCyclePermutationTmp = multiplyPermutations(sevenCyclePermutationTmp, sevenCyclePermutation)
                if nextTry:
                    break
            if not nextTry:
                amount += 1
                if printResult:
                    print("Cykl 7 w zapisie cyklowym: ")
                    print(add1ToCycleGroup(permutationToCycles(sevenCyclePermutation)))
                    print("grupa 7 permutacji z cyklami 2 w zapisie cyklowym: ")
                    for twoCyclePermutation in group:
                        print(add1ToCycleGroup(permutationToCycles(twoCyclePermutation)))
                    print()
                if amount and amount == limit:
                    return
    print(amount)


if __name__ == '__main__':
    file = open("result.txt", "a")
    # mainFunction(None, False)  # wyników 1680
    mainFunction(2, True)
    file.close()
