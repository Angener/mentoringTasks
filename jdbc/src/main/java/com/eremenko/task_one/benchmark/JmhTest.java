package com.eremenko.task_one.benchmark;

import com.eremenko.task_one.dao.DataBaseAccessorException;
import com.eremenko.task_one.dao.DatabaseAccessor;
import com.eremenko.task_one.dao.DatabaseAccessorFactory;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class JmhTest {


    DatabaseAccessor databaseAccessor =
            DatabaseAccessorFactory.getInstance().getDatabaseAccessor();

    @Benchmark
    public List<String> getTableDeprecatedTest() throws DataBaseAccessorException {
        return databaseAccessor.getTable("animals");
    }

    @Benchmark
    public List<String> getMethodTest() throws DataBaseAccessorException {
        return databaseAccessor.get("animals");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JmhTest.class.getSimpleName())
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.SECONDS)
                .warmupTime(TimeValue.seconds(2))
                .warmupIterations(5)
                .measurementTime(TimeValue.microseconds(500))
                .measurementIterations(1000)
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
