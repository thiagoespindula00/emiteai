export default interface Page<T> {
    content: T[],
    number: number,
    totalPages: number
}