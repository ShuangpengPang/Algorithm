# 有效电话号码

# Read from the file file.txt and output all valid phone numbers to stdout.

grep -P '^(\d{3}-|\(\d{3}\) )\d{3}-\d{4}$' file.txt

grep -P '^\d{3}-\d{3}-\d{4}$|^\(\d{3}\) \d{3}-\d{4}$' file.txt

sed -n -E '/^([0-9]{3}-|\([0-9]{3}\) )[0-9]{3}-[0-9]{4}$/p' file.txt
