package com.app.modules.common.utils.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class UpperDirective implements TemplateDirectiveModel {
	@Override
	public void execute(Environment env,
	                    Map params, TemplateModel[] loopVars,
	                    TemplateDirectiveBody body)
			throws TemplateException, IOException {
		// Check if no parameters were given:
		if (!params.isEmpty()) {
			throw new TemplateModelException(
					"This directive doesn't allow parameters.");
		}
		if (loopVars.length != 0) {
			throw new TemplateModelException(
					"This directive doesn't allow loop variables.");
		}

		// If there is non-empty nested content:
		if (body != null) {
			// Executes the nested body. Same as <#nested> in FTL, except
			// that we use our own writer instead of the current output writer.
			body.render(new UpperCaseFilterWriter(env.getOut()));
		} else {
			throw new RuntimeException("missing body");
		}
	}

	private static class UpperCaseFilterWriter extends Writer {

		private final Writer out;

		UpperCaseFilterWriter (Writer out) {
			this.out = out;
		}

		public void write(char[] cbuf, int off, int len)
				throws IOException {
			char[] transformedCbuf = new char[len];
			for (int i = 0; i < len; i++) {
				transformedCbuf[i] = Character.toUpperCase(cbuf[i + off]);
			}
			out.write(transformedCbuf);
		}

		public void flush() throws IOException {
			out.flush();
		}

		public void close() throws IOException {
			out.close();
		}
	}
}
