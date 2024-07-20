package io.github.ken3122.jmeter.functions;

import de.mkammerer.snowflakeid.SnowflakeIdGenerator;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.util.JMeterUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SnowflakeIdFunction extends AbstractFunction {

    private static final List<String> desc = new ArrayList<>();

    private static final String KEY = "__snowflakeId";

    private static final SnowflakeIdGenerator snowflakeIdGenerator =
            SnowflakeIdGenerator.createDefault(
                    JMeterUtils.getPropDefault("snowflake_generator_id", 0)
            );

    public SnowflakeIdFunction() {
    }

    @Override
    public String execute(SampleResult previousResult, Sampler currentSampler) throws InvalidVariableException {
        return Long.toString(snowflakeIdGenerator.next());
    }

    @Override
    public void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
        checkParameterCount(parameters, 0, 0);
    }

    @Override
    public String getReferenceKey() {
        return KEY;
    }

    @Override
    public List<String> getArgumentDesc() {
        return desc;
    }

}
