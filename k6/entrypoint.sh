#!/bin/sh

while true
do
  echo "====== K6 시나리오 파일 목록 ======"
  ls /scripts/*.js
  echo ""
  echo "실행할 파일명을 입력하세요 (또는 q 입력 시 종료):"
  read -r filename

  if [ "$filename" = "q" ]; then
    echo "종료합니다 👋"
    break
  fi

  if [ ! -f "/scripts/$filename" ]; then
    echo "❌ 해당 파일이 존재하지 않습니다: $filename"
    continue
  fi

  echo "🎯 실행 중... ($filename)"
  k6 run "/scripts/$filename"
  echo ""
  echo "✅ [$filename] 시나리오 실행 완료!"
  echo ""
done
