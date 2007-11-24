/**
 * Copyright (c) 2007, Yahoo! Inc. All rights reserved.
 * Copyright (c) 2007, Viktor Lieskovsky
 *
 * The YUI Compressor was written and is maintained by:
 *     	Julien Lecomte <jlecomte@yahoo-inc.com>
 * The Ant task was written by:
 *      Viktor Lieskovsky <viktor.lieskovsky@gmail.com>
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright notice,
 *       this list of conditions and the following disclaimer in the documentation
 *       and/or other materials provided with the distribution.
 *     * Neither the name of the <ORGANIZATION> nor the names of its contributors
 *       may be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.noha.tools.ant.yuicompressor.tasks;

import java.io.File;

/**
 * @author Viktor Lieskovsky
 */
public class CompressionStatistics {

  private long fromBytes = 0L;
  private long toBytes = 0L;
  private long fileCount = 0L;

  public String getFileStats(File inFile, File outFile) {
    // update cummulated statistics
    fromBytes += inFile.length();
    toBytes += outFile.length();
    fileCount++;

    int percentage = (int) Math.floor((double) outFile.length() / (double) inFile.length() * 100);
    return "[" + percentage + "%] " + inFile.getName() + " [" + inFile.length() + "] ---> " + outFile.getName() + " ["
        + outFile.length() + "]";
  }

  public String getTotalStats() {
    if (fileCount == 0) {
      return "No files to compress, or all files already up to date";
    }

    int percentage = (int) Math.floor((double) toBytes / (double) fromBytes * 100);
    long fromKBytes = (long) Math.floor((double) fromBytes / (double) 1024);
    long toKBytes = (long) Math.floor((double) toBytes / (double) 1024);
    long saved = fromKBytes - toKBytes;
    return "Compressed " + fileCount + " files to " + percentage + "% (" + fromKBytes + "KB to " + toKBytes
        + "KB, saving " + saved + "KB)";
  }

}
