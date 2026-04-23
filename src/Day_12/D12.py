from pathlib import Path


def read_board(path):
    with path.open() as file:
        return [list(line.strip()) for line in file if line.strip()]


def valid_grid(board, row, col):
    return 0 <= row < len(board) and 0 <= col < len(board[0])


def additional_perims(board, row, col, char):
    count = 0

    if not valid_grid(board, row - 1, col) or board[row - 1][col] != char:
        count += 1
    if not valid_grid(board, row + 1, col) or board[row + 1][col] != char:
        count += 1
    if not valid_grid(board, row, col - 1) or board[row][col - 1] != char:
        count += 1
    if not valid_grid(board, row, col + 1) or board[row][col + 1] != char:
        count += 1

    return count


def dfs(board, used, row, col, char, region_id):
    if not valid_grid(board, row, col) or used[row][col] != 0 or board[row][col] != char:
        return 0

    used[row][col] = region_id
    count = additional_perims(board, row, col, char)

    return (
        count
        + dfs(board, used, row + 1, col, char, region_id)
        + dfs(board, used, row - 1, col, char, region_id)
        + dfs(board, used, row, col + 1, char, region_id)
        + dfs(board, used, row, col - 1, char, region_id)
    )


def count_area(used, region_id):
    return sum(cell == region_id for row in used for cell in row)


def question1(board):
    rows, cols = len(board), len(board[0])
    used = [[0] * cols for _ in range(rows)]
    result = 0
    region_id = 1

    for row in range(rows):
        for col in range(cols):
            if used[row][col] == 0:
                region_id += 1
                perimeter = dfs(board, used, row, col, board[row][col], region_id)
                area = count_area(used, region_id)
                result += perimeter * area

    return result


def main():
    board = read_board(Path(__file__).with_name("input.txt"))
    print(f"Q1: {question1(board)}")


if __name__ == "__main__":
    main()
