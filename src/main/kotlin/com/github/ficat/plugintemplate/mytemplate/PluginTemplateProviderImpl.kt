package com.github.ficat.plugintemplate.mytemplate

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.ficat.plugintemplate.mytemplate.mvp.MvpTemplate
import com.github.ficat.plugintemplate.mytemplate.pool.PoolTemplate

class PluginTemplateProviderImpl : WizardTemplateProvider()  {
    override fun getTemplates(): List<Template> = listOf(
        MvpTemplate,PoolTemplate,
    )
}