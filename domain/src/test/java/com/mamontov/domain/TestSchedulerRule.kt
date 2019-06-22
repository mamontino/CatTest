package com.mamontov.domain

import io.reactivex.Scheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestSchedulerRule(val scheduler: Scheduler = Schedulers.trampoline()) : TestRule {

	override fun apply(base: Statement, d: Description): Statement {
		return object : Statement() {
			override fun evaluate() {
				RxJavaPlugins.setIoSchedulerHandler { scheduler }
				RxJavaPlugins.setComputationSchedulerHandler { scheduler }
				RxJavaPlugins.setNewThreadSchedulerHandler { scheduler }
				RxJavaPlugins.setSingleSchedulerHandler { scheduler }

				try {
					base.evaluate()
				} finally {
					RxJavaPlugins.reset()
				}
			}
		}
	}
}