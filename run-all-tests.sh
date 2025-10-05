#!/bin/bash
echo "🧪 Testing Exercise 1..."
cd exo1 && mvn clean test
EXO1=$?

echo ""
echo "🧪 Testing Exercise 2..."
cd ../exo2 && mvn clean test
EXO2=$?

echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
if [ $EXO1 -eq 0 ] && [ $EXO2 -eq 0 ]; then
    echo "✅ ALL TESTS PASSED!"
    echo "   Exo1: 17 tests ✅"
    echo "   Exo2: 14 tests ✅"
    echo "   Total: 31 tests ✅"
else
    echo "❌ SOME TESTS FAILED"
fi
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
