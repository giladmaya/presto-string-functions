package io.prestosql.string.functions;

import io.prestosql.spi.Plugin;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class StringFunctionsPlugin
    implements Plugin
{
    @Override
    public Set<Class<?>> getFunctions()
    {
        return ImmutableSet.<Class<?>>builder()
                .add(TrimFunctions.class)
                .build();
    }
}