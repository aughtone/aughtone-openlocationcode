import math

CODE_ALPHABET = "23456789CFGHJMPQRVWX"
SEPARATOR = '+'

def encodeIntegers(lat, lng, codeLength):
    codeLength = min(codeLength, 15)
    
    revCodeBuilder = []
    
    if codeLength > 10:
        for i in range(5):
            latDigit = lat % 5
            lngDigit = lng % 4
            ndx = int(latDigit * 4 + lngDigit)
            revCodeBuilder.append(CODE_ALPHABET[ndx])
            lat = lat // 5
            lng = lng // 4
    else:
        lat = int(lat / (5 ** 5))
        lng = int(lng / (4 ** 5))
        
    for i in range(5):
        revCodeBuilder.append(CODE_ALPHABET[int(lng % 20)])
        revCodeBuilder.append(CODE_ALPHABET[int(lat % 20)])
        lat = lat // 20
        lng = lng // 20
        if i == 0:
            revCodeBuilder.append(SEPARATOR)
            
    codeBuilder = "".join(reversed(revCodeBuilder))
    
    if codeLength < 8:
        for i in range(codeLength, 8):
            codeBuilder = codeBuilder[:i] + '0' + codeBuilder[i+1:]
            
    return codeBuilder[:max(9, codeLength + 1)]

print(encodeIntegers(3265000000, 2537062400, 8))
