# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.


def findAllPairPermutations():
    result = []
    permutation = [0, 1, 2, 3, 4, 5, 6, 7]
    for i in range(1, 8):  # od 1 do 7 bo numeracja od 0!
        firstPair = 0
        secondPair = 1
        permutation[firstPair] = i
        permutation[i] = firstPair
        if i == 1:
            secondPair = 2
        for j in range(2, 8):
            thirdPair = 2
            if i == 1:
                thirdPair = 3
            if i == 2:
                thirdPair = 3
            if j != i and j != secondPair:
                permutation[secondPair] = j
                permutation[j] = secondPair
                while thirdPair == i or thirdPair == j:
                    thirdPair += 1
                for k in range(3, 8):
                    forthPair = 3
                    if k != i and k != secondPair and k != j and k != thirdPair:
                        permutation[thirdPair] = k
                        permutation[k] = thirdPair
                        while forthPair == i or forthPair == j or forthPair == k or forthPair == thirdPair:
                            forthPair += 1
                        for l in range(4, 8):
                            if l != i and l != secondPair and l != j and l != thirdPair and l != k and l != forthPair:
                                permutation[forthPair] = l
                                permutation[l] = forthPair
                                result.append(permutation.copy())
    return result


def multiplyPermutations(per1, per2):
    res = [0, 1, 2, 3, 4, 5, 6, 7]
    for i in range(0, len(per1)):
        res[i] = per2[per1[i]]
    return res


def isPairPermutation(permutation):
    for i in range(0, len(permutation)):
        if permutation[permutation[i]] != i or permutation[i] == i:
            return False
    return True


def find7cyclePermutations():
    all = []
    first = 0
    for second in range(1, 7):
        for third in range(1, 7):
            result = [first, second, -1, -1, -1, -1, -1]
            if third not in result:
                for forth in range(1, 7):
                    result = [first, second, third, -1, -1, -1, -1]
                    if forth not in result:
                        for fifth in range(1, 7):
                            result = [first, second, third, forth, -1, -1, -1]
                            if fifth not in result:
                                for sixth in range(1, 7):
                                    result = [first, second, third, forth, fifth, -1, -1]
                                    if sixth not in result:
                                        for seventh in range(1, 7):
                                            result = [first, second, third, forth, fifth, sixth, -1]
                                            if seventh not in result:
                                                result[6] = seventh
                                                all.append(cycleToPermutration(result.copy()))
    return all


def cycleToPermutration(cycle):
    permutation = [0, 1, 2, 3, 4, 5, 6, 7]
    for i in range(0, len(cycle)):
        permutation[cycle[i]] = cycle[(i+1) % len(cycle)]
    return permutation


def permutationToCycle(permutation):
    used = []
    cycle = []
    while len(used) < 8:
        cycleN = []
        for i in range(0, len(permutation)):
            if i not in used:
                cycleN.append(i)
                used.append(i)
                j = i
                while permutation[j] != cycleN[0]:
                    j = permutation[j]
                    cycleN.append(j)
                    used.append(j)
                cycle.append(cycleN.copy())
                break
    return cycle


def hasOneNotHaoticPlace(permutation):
    res = 0
    for i in range(0, len(permutation)):
        if permutation[i] == i:
            res += 1
            if res > 1:
                return False
    if res == 1:
        return True
    return False


def cycleToBeFrom1(cycle):
    for i in range(0, len(cycle)):
        for j in range(0, len(cycle[i])):
            cycle[i][j]+=1
    return cycle


if __name__ == '__main__':
    pairPermutations = findAllPairPermutations()
    print(len(pairPermutations))
    end = False
    file = open("result.txt", "a")
    results = []
    sevenCycle = find7cyclePermutations()
    for per1 in pairPermutations:
        if end:
            break
        for per2 in pairPermutations:
            if end:
                break
            if per1 != per2:
                per3 = multiplyPermutations(per1, per2)
                if isPairPermutation(per3) and multiplyPermutations(per3, per1) == per2:
                    for per4 in pairPermutations:
                        if per4 != per1 and per4 != per2 and per4 != per3:
                            per5 = multiplyPermutations(per1, per4)
                            per6 = multiplyPermutations(per2, per4)
                            per7 = multiplyPermutations(per3, per4)
                            if isPairPermutation(per5) \
                                    and isPairPermutation(per6) \
                                    and isPairPermutation(per7) \
                                    and (multiplyPermutations(per5, per2) == per3
                                         or multiplyPermutations(per5, per2) == per6
                                         or multiplyPermutations(per5, per2) == per7):
                                if per5 != per2 and per5 != per3:
                                    if per1 not in results or \
                                            per2 not in results or \
                                            per3 not in results or \
                                            per4 not in results or \
                                            per5 not in results or \
                                            per6 not in results or \
                                            per7 not in results:
                                        results.append(per1)
                                        results.append(per2)
                                        results.append(per3)
                                        results.append(per4)
                                        results.append(per5)
                                        results.append(per6)
                                        results.append(per7)
                                        # file.write("result " + str(int(len(results)/7)) + " permutacje z cyklami 2:\n")
                                        # file.write(','.join(list(map(lambda x: str(x + 1), per1))) + "\n")
                                        # file.write(','.join(list(map(lambda x: str(x + 1), per2))) + "\n")
                                        # file.write(','.join(list(map(lambda x: str(x + 1), per3))) + "\n")
                                        # file.write(','.join(list(map(lambda x: str(x + 1), per4))) + "\n")
                                        # file.write(','.join(list(map(lambda x: str(x + 1), per5))) + "\n")
                                        # file.write(','.join(list(map(lambda x: str(x + 1), per6))) + "\n")
                                        # file.write(','.join(list(map(lambda x: str(x + 1), per7))) + "\n")
    for res in range(0, int(len(results) / 7)):  # dla kazdego wyniku
        for j in sevenCycle:  # dla kazdego z 7-cyklowych
            nextTry = False
            # if nextTry:
            #     break
            for i in range(7 * res, res * 7 + 7):  # dla kazdego z 7 wynikow podwojnych cykli
                sevenCycleTmp = j.copy()
                while sevenCycleTmp != [0, 1, 2, 3, 4, 5, 6, 7]:
                    if not hasOneNotHaoticPlace(multiplyPermutations(j, results[i])) and not hasOneNotHaoticPlace(multiplyPermutations(results[i], j)):
                        nextTry = True
                        break
                    if sevenCycleTmp in sevenCycle:
                        sevenCycle.remove(sevenCycleTmp)
                    sevenCycleTmp = multiplyPermutations(sevenCycleTmp, j)
                if nextTry:
                    break
            if not nextTry:
                # print("Cykl 7 w zapisie NIE cyklowym: ")
                # print("[" + ', '.join(list(map(lambda x: str(x + 1), j))) + "]")
                # print("grupa 7 permutacji z cyklami 2 w zapisie NIE cyklowym: " + str(res + 1))
                # for i in range(7 * res, res * 7 + 7):
                #     print("[" + ', '.join(list(map(lambda x: str(x + 1), results[i]))) + "]")
                print("Cykl 7 w zapisie cyklowym: ")
                print(cycleToBeFrom1(permutationToCycle(j)))
                print("grupa 7 permutacji z cyklami 2 w zapisie cyklowym: " + str(res + 1))
                for i in range(7 * res, res * 7 + 7):
                    print(cycleToBeFrom1(permutationToCycle(results[i])))
                # print("mnozenie kazdej z 7 cyklowej grupy z kazda z 2 cyklowej grupy: ")
                # index = 0
                # for i in range(7 * res, res * 7 + 7):  # dla kazdego z 7 wynikow podwojnych cykli
                #     sevenCycleTmp = j.copy()
                #     while sevenCycleTmp != [0, 1, 2, 3, 4, 5, 6, 7]:
                #         index += 1
                #         print(str(index) + ":", cycleToBeFrom1(permutationToCycle(multiplyPermutations(sevenCycleTmp, results[i]))))
                #         sevenCycleTmp = multiplyPermutations(sevenCycleTmp, j)

                for res in range(0, int(len(results) / 7)):  # dla kazdego wyniku
                    nextTry = False
                    # if nextTry:
                    #     break
                    for i in range(7 * res, res * 7 + 7):  # dla kazdego z 7 wynikow podwojnych cykli
                        sevenCycleTmp = j.copy()
                        while sevenCycleTmp != [0, 1, 2, 3, 4, 5, 6, 7]:
                            if not hasOneNotHaoticPlace(
                                    multiplyPermutations(j, results[i])) and not hasOneNotHaoticPlace(
                                    multiplyPermutations(results[i], j)):
                                nextTry = True
                                break
                            if sevenCycleTmp in sevenCycle:
                                sevenCycle.remove(sevenCycleTmp)
                            sevenCycleTmp = multiplyPermutations(sevenCycleTmp, j)
                        if nextTry:
                            break
                    if not nextTry:
                        # print("Cykl 7 w zapisie NIE cyklowym: ")
                        # print("[" + ', '.join(list(map(lambda x: str(x + 1), j))) + "]")
                        # print("grupa 7 permutacji z cyklami 2 w zapisie NIE cyklowym: " + str(res + 1))
                        # for i in range(7 * res, res * 7 + 7):
                        #     print("[" + ', '.join(list(map(lambda x: str(x + 1), results[i]))) + "]")
                        print("Cykl 7 w zapisie cyklowym: ")
                        print(cycleToBeFrom1(permutationToCycle(j)))
                        print("grupa 7 permutacji z cyklami 2 w zapisie cyklowym: " + str(res + 1))
                        for i in range(7 * res, res * 7 + 7):
                            print(cycleToBeFrom1(permutationToCycle(results[i])))
                print(len(results) / 7)
                file.close()
                quit()
    file.close()

