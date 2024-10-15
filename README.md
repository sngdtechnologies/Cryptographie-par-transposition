# Transposition algorithm

## Principle of operation of the algorithm.

- Request and Recover text to be encrypted and encryption key
- Remove spaces from text
- Create and fill an associative array $assoc of a sequence of plaintext arrays of the same length as the key 
- Reorganization of the associative array by block and ascending key character order
- Transformation of the final associative array into a carat string
- Display of the encrypted character string
