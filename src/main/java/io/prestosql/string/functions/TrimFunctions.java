package io.prestosql.string.functions;

import io.airlift.slice.Slice;
import io.airlift.slice.Slices;
import io.prestosql.spi.function.*;
import io.prestosql.spi.type.StandardTypes;
import io.prestosql.operator.scalar.StringFunctions;
import io.prestosql.type.BigintOperators;
import io.prestosql.type.VarcharOperators;

public class TrimFunctions
{
    private static long DEFAULT_TRIM = 4;

    @ScalarFunction("ltrim")
    @Description("Trims numTokens from the left")
    @LiteralParameters("x")
    @SqlType("varchar(x)")
    public static Slice leftTrim(
        @SqlNullable @SqlType("varchar(x)") Slice value,
        @SqlType(StandardTypes.BIGINT) long numTokens)
    {
        if (value == null) {
            return value;
        }
        return StringFunctions.substring(value, numTokens + 1);
    }

    @ScalarFunction("ltrim")
    @Description("Trims 4 tokens from the left")
    @LiteralParameters("x")
    @SqlType("varchar(x)")
    public static Slice leftTrim(
        @SqlNullable @SqlType("varchar(x)") Slice value)
    {
        return leftTrim(value, DEFAULT_TRIM);
    }

    @ScalarFunction("ltrim")
    @Description("Trims numDigits from the left")
    @SqlType(StandardTypes.BIGINT)
    public static Long leftTrim(
        @SqlType(StandardTypes.BIGINT) long value,
        @IsNull boolean isValueNull,
        @SqlType(StandardTypes.BIGINT) long numDigits)
    {
        if (isValueNull) {
            return null;
        }

        Slice result = leftTrim(BigintOperators.castToVarchar(value), numDigits);

        if (result == Slices.EMPTY_SLICE) {
            return null;
        }

        return Long.parseLong(result.toStringUtf8());
    }

    @ScalarFunction("ltrim")
    @Description("Trims 4 from the left")
    @SqlType(StandardTypes.BIGINT)
    public static Long leftTrim(
        @SqlType(StandardTypes.BIGINT) long value,
        @IsNull boolean isValueNull)
    {
        return leftTrim(value, isValueNull, DEFAULT_TRIM);
    }
}
