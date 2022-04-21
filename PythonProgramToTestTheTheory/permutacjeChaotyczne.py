import pathlib
from threading import Thread


N = 5


def findAllChaotic():
    global file
    global amount1
    path = pathlib.Path(__file__).parent.resolve().__str__() + "/permutacjeChaotyczne.txt"
    file = open(path, "w")
    amount1 = 0

    threads = []
    for index in range(2, N+1):
        tab = [x for x in range(1,N+1)]
        tab.remove(index)
        threads.append(Thread(target = _findAllChaoticRecursive, args = ([index],tab)))
        threads[index-2].start()

    for t in threads:
        t.join()

    print(amount1)
    file.close()

def _findAllChaoticRecursive(result, unused):
    global file
    global amount1
    for i in unused:
        if i == len(result)+1:
            continue
        newUnused = unused.copy()
        newUnused.remove(i)
        newResult = result.copy()
        newResult.append(i)
        if len(newUnused) == 0:
            file.write(str("".join(map(str, newResult))) + "\n")
            amount1 += 1
            return
        _findAllChaoticRecursive(newResult, newUnused)



if __name__ == '__main__':
    findAllChaotic()
